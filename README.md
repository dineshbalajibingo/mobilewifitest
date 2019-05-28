Configuration Changes :-

web.xml
Initial loading be triggered through web.xml changes which will be invoking initial login jsp(any other customized jsp) page , spring security configuration xml file and spring config xml files.

spring-config.xml
Main spring configuration file with bean definitions . It will be changed to have jndiName as Weblogic/websphere/jboss datasource name. Schema UTOTDMC and datasource
tdmUserDS will be configured here . JpaVendorAdapter Bean definition will be to have databasePlatform value setup here - example Oracle9i or MSSQL /Mysql.
Bean definition of entityManagerFactoryUser shall have new datasource property definition. 
<bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
            <property name="generateDdl" value="true" />
            <property name="database" value="SQL_SERVER" />
			<property name="databasePlatform" value="org.hibernate.dialect.SQLServerDialect" />
        </bean>
		
		
All the dao implementation classes will be changed for connection with specific jndi template of server.

RefreshDAOImpl.java
TDMAdminDAOImpl.java
TDMDataMaskingDAOImpl.java
TdmMyReservationDAOImpl.java
TdmPolicyAutoPropSearchDAOImpl.java
TdmPolicySearchDAOImpl.java
TDMProviserSearchDAOImpl.java


Persistent.xml
All the POJO classes related to database tables shall be defined here. Any modification (addition/drop) of tables shall impact changes in this xml
<property name="hibernate.dialect" value="org.hibernate.dialect.SQLServerDialect" />
            <property name="hibernate.connection.url" value="${jdbc.url}" />
            <property name="hibernate.connection.driver_class" value="${jdbc.driverClass}" />
            <property name="hibernate.connection.username" value="${jdbc.user}" />
            <property name="hibernate.connection.password" value="${jdbc.pwd}" />
            <property name="hibernate.hbm2ddl.auto" value="create-drop" /> 

Spring -security.xml
Global method level security shall e enabled/disabled from here. Access denied handler 	error page shall be defined here. Role based access based on 
url patterns. Login processing url and maximum sessions to be defined here for concurrency control.
Authentication manager  division will be changed to point to new datasource where user id , password , authorities shall be retrieved from TDM_USERS and TDM_USERS_AUTH tables.


UI Changes :-

Workflow -

 login.jsp and other jsp invokes main.js . Jquery functions are used to load various forms of page(ex. testDataForm, testDataFormClaim, testDataFormClaim,#dataMaskingForm
 dataRefreshForm, dataSubsettingForm,dataChMgmtForm, dataOnboardReqForm.
 jquery .ajax method is used to post urls to the controller via AppConstants defined. For instance , TDMOnBoardReqController is invoked for url /tdmOnboardReq and request mapping of
 tdmPostOnboardReq method is done with this url based on request method  POST. Log4J loggig is done.
 tdmDataMaskingService.getSaveOnboardingReq is invoked . Model attributes are passed. tdmOnboardReqDTO.getOnboardReqId()is returned after exception handling.
 
 Index.jsp  invokes services related to Masking Request, TDM On-boarding Request, Change Request, Sub Setting Request, Data Refresh Request via /tdmDataMaskingNew, /tdmOnboardReq,/tdmChangeReqExt,/tdmDataSubsetting
 /tdmDataRefresh. Corresponding controllers are invoked TDMMaskingController, TDMOnBoardReqController, TDMChangeReqController, TDMSubscSearchController.



example workflow of /policyProp url TdmPolicyPropertySearchController class will do request method mapping of service tdmPolicyAutoSearchService. Business logic resides in the 
methods searchPolicyPropRecordsByPolicySearchNew, saveReservedData and henceforth DTO objects are returned tdmPolicyPropertySearchDTO,TdmPolicyPropertySearchResultDTO by these methods. Getters of DTO class tdmPolicyPropertySearchDTO are invoked to build search criteria.
 And result set is build in TdmPolicyPropertySearchResultDTO via related field setter methods. There further versions of request mapping methods in service to handle no. or records and do pagenation. Logic build if result set is not null.
 
 Mapper classes are invoked TdmPolicyAutoPropSearchMapperImpl which will map DTO fields with corresponding DO fields like reservationDO,policySummaryStgDO
 Model attributes are added in the controller with these DTO as argument and corresponding views are returned example - propertyPolicySearch(JSP) ,searchPolicyPropListExcelView(JAVA class)
