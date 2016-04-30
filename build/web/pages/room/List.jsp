<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Room Items</title>
            <link rel="stylesheet" type="text/css" href="/JsfJpaCrud/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Room Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Room Items Found)<br />" rendered="#{room.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{room.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{room.pagingInfo.firstItem + 1}..#{room.pagingInfo.lastItem} of #{room.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{room.prev}" value="Previous #{room.pagingInfo.batchSize}" rendered="#{room.pagingInfo.firstItem >= room.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{room.next}" value="Next #{room.pagingInfo.batchSize}" rendered="#{room.pagingInfo.lastItem + room.pagingInfo.batchSize <= room.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{room.next}" value="Remaining #{room.pagingInfo.itemCount - room.pagingInfo.lastItem}"
                                   rendered="#{room.pagingInfo.lastItem < room.pagingInfo.itemCount && room.pagingInfo.lastItem + room.pagingInfo.batchSize > room.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{room.roomItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="RoomID"/>
                            </f:facet>
                            <h:outputText value="#{item.roomID}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="MaxPlaces"/>
                            </f:facet>
                            <h:outputText value="#{item.maxPlaces}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="RoomPrice"/>
                            </f:facet>
                            <h:outputText value="#{item.roomPrice}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{room.detailSetup}">
                                <f:param name="jsfcrud.currentRoom" value="#{jsfcrud_class['jpa.controllers.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][room.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{room.editSetup}">
                                <f:param name="jsfcrud.currentRoom" value="#{jsfcrud_class['jpa.controllers.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][room.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{room.remove}">
                                <f:param name="jsfcrud.currentRoom" value="#{jsfcrud_class['jpa.controllers.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][room.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{room.createSetup}" value="New Room"/>
                <br />


            </h:form>
        </body>
    </html>
</f:view>
