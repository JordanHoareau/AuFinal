<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Room</title>
            <link rel="stylesheet" type="text/css" href="/JsfJpaCrud/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Room</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{room.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="RoomID:"/>
                    <h:inputText id="roomID" value="#{room.room.roomID}" title="RoomID" required="true" requiredMessage="The roomID field is required." />
                    <h:outputText value="MaxPlaces:"/>
                    <h:inputText id="maxPlaces" value="#{room.room.maxPlaces}" title="MaxPlaces" />
                    <h:outputText value="RoomPrice:"/>
                    <h:inputText id="roomPrice" value="#{room.room.roomPrice}" title="RoomPrice" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{room.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{room.listSetup}" value="Show All Room Items" immediate="true"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
