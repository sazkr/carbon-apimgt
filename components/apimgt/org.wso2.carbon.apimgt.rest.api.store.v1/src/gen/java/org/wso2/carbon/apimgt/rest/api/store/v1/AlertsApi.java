package org.wso2.carbon.apimgt.rest.api.store.v1;

import org.wso2.carbon.apimgt.rest.api.store.v1.dto.AlertConfigDTO;
import org.wso2.carbon.apimgt.rest.api.store.v1.dto.AlertConfigListDTO;
import org.wso2.carbon.apimgt.rest.api.store.v1.dto.AlertTypesListDTO;
import org.wso2.carbon.apimgt.rest.api.store.v1.dto.AlertsInfoDTO;
import org.wso2.carbon.apimgt.rest.api.store.v1.dto.ErrorDTO;
import org.wso2.carbon.apimgt.rest.api.store.v1.AlertsApiService;
import org.wso2.carbon.apimgt.rest.api.store.v1.impl.AlertsApiServiceImpl;
import org.wso2.carbon.apimgt.api.APIManagementException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.inject.Inject;

import io.swagger.annotations.*;
import java.io.InputStream;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
@Path("/alerts")

@Api(description = "the alerts API")
@Consumes({ "application/json" })
@Produces({ "application/json" })


public class AlertsApi  {

  @Context MessageContext securityContext;

AlertsApiService delegate = new AlertsApiServiceImpl();


    @POST
    @Path("/{userName}/config")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Add AbnormalRequestsPerMin alert configurations. ", notes = "This operation is used to add configuration for the AbnormalRequestsPerMin alert type. ", response = AlertConfigDTO.class, authorizations = {
        @Authorization(value = "OAuth2Security", scopes = {
            
        })
    }, tags={ "Alerts",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created. Successful response with newly created object as entity. Location header contains URL of newly created entity. ", response = AlertConfigDTO.class),
        @ApiResponse(code = 400, message = "Bad Request The request parameters validation failed. ", response = ErrorDTO.class),
        @ApiResponse(code = 404, message = "Not Found. The user does not exist in the system. ", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Internal Server Error An error occurred while retrieving subscribed alert types by user. ", response = ErrorDTO.class) })
    public Response configureAlertUser(@ApiParam(value = "The name of the user. ",required=true) @PathParam("userName") String userName, @ApiParam(value = "Configuration for AbnormalRequestCount alert type" ,required=true) AlertConfigDTO body) throws APIManagementException{
        return delegate.configureAlertUser(userName, body, securityContext);
    }

    @DELETE
    @Path("/{userName}/config")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Delete the selected configuration from AbnormalRequestsPerMin alert type. ", notes = "This operation is used to delete configuration from the AbnormalRequestsPerMin alert type. ", response = Void.class, authorizations = {
        @Authorization(value = "OAuth2Security", scopes = {
            
        })
    }, tags={ "Alerts",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK. The alert config is deleted successfully. ", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request The request parameters validation failed. ", response = ErrorDTO.class),
        @ApiResponse(code = 404, message = "Not Found. The user does not exist in the system. ", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Internal Server Error An error occurred while retrieving subscribed alert types by user. ", response = ErrorDTO.class) })
    public Response deleteAlertConfigUser(@ApiParam(value = "The name of the user. ",required=true) @PathParam("userName") String userName, @ApiParam(value = "The AbnormalRequestCount configurations that should be deleted" ,required=true) AlertConfigDTO body) throws APIManagementException{
        return delegate.deleteAlertConfigUser(userName, body, securityContext);
    }

    @GET
    @Path("/{userName}/config")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Get the AbnormalRequestsPerMin alert configurations ", notes = "This operation is used to get configurations of the AbnormalRequestsPerMin alert type. ", response = AlertConfigListDTO.class, authorizations = {
        @Authorization(value = "OAuth2Security", scopes = {
            
        })
    }, tags={ "Alerts",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK. The store alert configuration. ", response = AlertConfigListDTO.class),
        @ApiResponse(code = 404, message = "Not Found. The user does not exist in the system. ", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Internal Server Error An error occurred while retrieving subscribed alert types by user. ", response = ErrorDTO.class) })
    public Response getAlertConfigsUser(@ApiParam(value = "The name of the user. ",required=true) @PathParam("userName") String userName) throws APIManagementException{
        return delegate.getAlertConfigsUser(userName, securityContext);
    }

    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Get the list of API Store alert types. ", notes = "This operation is used to get the list of supportd alert types for the 'subscriber' agent. ", response = AlertTypesListDTO.class, authorizations = {
        @Authorization(value = "OAuth2Security", scopes = {
            
        })
    }, tags={ "Alerts",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK. The list of subscriber alert types are returned. ", response = AlertTypesListDTO.class),
        @ApiResponse(code = 500, message = "Internal Server Error. An internal server error occurred while retrieving the alert types. ", response = ErrorDTO.class) })
    public Response getStoreAlertTypes() throws APIManagementException{
        return delegate.getStoreAlertTypes(securityContext);
    }

    @GET
    @Path("/{userName}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Get the list of API Store alert types subscribed by the user. ", notes = "This operation is used to get the list of subscribed alert types by the user. ", response = AlertsInfoDTO.class, authorizations = {
        @Authorization(value = "OAuth2Security", scopes = {
            
        })
    }, tags={ "Alerts",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK. The list of subscribed alert types are returned. ", response = AlertsInfoDTO.class),
        @ApiResponse(code = 404, message = "Not Found. The user does not exist in the system. ", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Internal Server Error An error occurred while retrieving subscribed alert types by user. ", response = ErrorDTO.class) })
    public Response getSubscribedAlertTypesByUser(@ApiParam(value = "The name of the user. ",required=true) @PathParam("userName") String userName) throws APIManagementException{
        return delegate.getSubscribedAlertTypesByUser(userName, securityContext);
    }

    @POST
    @Path("/{userName}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Subscribe to the selected alert types by the user. ", notes = "This operation is used to get the list of subscribed alert types by the user. ", response = AlertsInfoDTO.class, authorizations = {
        @Authorization(value = "OAuth2Security", scopes = {
            
        })
    }, tags={ "Alerts",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "OK. Successful response with the newly subscribed alerts. ", response = AlertsInfoDTO.class),
        @ApiResponse(code = 400, message = "Bad Request. Invalid Request or request validation failure. ", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found. The user not found. ", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Internal Server Error An internal server error occurred while subscribing to alerts. ", response = ErrorDTO.class) })
    public Response subscribeToAlertsByUser(@ApiParam(value = "The name of the user. ",required=true) @PathParam("userName") String userName, @ApiParam(value = "The alerts list and the email list to subscribe." ,required=true) AlertsInfoDTO body) throws APIManagementException{
        return delegate.subscribeToAlertsByUser(userName, body, securityContext);
    }

    @DELETE
    @Path("/{userName}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Unsubscribe user from all the alert types. ", notes = "This operation is used to unsubscribe the respective user from all the alert types. ", response = Void.class, authorizations = {
        @Authorization(value = "OAuth2Security", scopes = {
            
        })
    }, tags={ "Alerts" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK. The user is unsubscribed from the alerts successfully. ", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found. Requested user is not found in the system. ", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Internal Server Error ", response = ErrorDTO.class) })
    public Response unsubscribeAllAlerts(@ApiParam(value = "The name of the user. ",required=true) @PathParam("userName") String userName) throws APIManagementException{
        return delegate.unsubscribeAllAlerts(userName, securityContext);
    }
}
