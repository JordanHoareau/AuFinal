<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Room</title>
            <link rel="stylesheet" type="text/css" href="/JsfJpaCrud/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Room</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="RoomID:"/>
                    <h:outputText value="#{room.room.roomID}" title="RoomID" />
                    <h:outputText value="MaxPlaces:"/>
                    <h:inputText id="maxPlaces" value="#{room.room.maxPlaces}" title="MaxPlaces" />
                    <h:outputText value="RoomPrice:"/>
                    <h:inputText id="roomPrice" value="#{room.room.roomPrice}" title="RoomPrice" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{room.edit}" value="Save">
                    <f:param name="jsfcrud.currentRoom" value="#{jsfcrud_class['jpa.controllers.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][room.room][room.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{room.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentRoom" value="#{jsfcrud_class['jpa.controllers.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][room.room][room.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{room.listSetup}" value="Show All Room Items" immediate="true"/>
                <br />

            </h:form>
        </body>
    </html>
</f:view>
