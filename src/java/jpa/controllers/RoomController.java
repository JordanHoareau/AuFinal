/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import jpa.controllers.util.JsfUtil;
import jpa.controllers.util.PagingInfo;
import jpa.entities.Room;

/**
 *
 * @author Jé
 */
public class RoomController {

    public RoomController() {
        pagingInfo = new PagingInfo();
        converter = new RoomConverter();
    }
    private Room room = null;
    private List<Room> roomItems = null;
    private RoomFacade jpaController = null;
    private RoomConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "JsfJpaCrudPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public RoomFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (RoomFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "roomJpa");
        }
        return jpaController;
    }

    public SelectItem[] getRoomItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getRoomItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Room getRoom() {
        if (room == null) {
            room = (Room) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentRoom", converter, null);
        }
        if (room == null) {
            room = new Room();
        }
        return room;
    }

    public String listSetup() {
        reset(true);
        return "room_list";
    }

    public String createSetup() {
        reset(false);
        room = new Room();
        return "room_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(room);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Room was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("room_detail");
    }

    public String editSetup() {
        return scalarSetup("room_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        room = (Room) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentRoom", converter, null);
        if (room == null) {
            String requestRoomString = JsfUtil.getRequestParameter("jsfcrud.currentRoom");
            JsfUtil.addErrorMessage("The room with id " + requestRoomString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String roomString = converter.getAsString(FacesContext.getCurrentInstance(), null, room);
        String currentRoomString = JsfUtil.getRequestParameter("jsfcrud.currentRoom");
        if (roomString == null || roomString.length() == 0 || !roomString.equals(currentRoomString)) {
            String outcome = editSetup();
            if ("room_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit room. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(room);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Room was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentRoom");
        Integer id = new Integer(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Room was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Room> getRoomItems() {
        if (roomItems == null) {
            getPagingInfo();
            roomItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return roomItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "room_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "room_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        room = null;
        roomItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Room newRoom = new Room();
        String newRoomString = converter.getAsString(FacesContext.getCurrentInstance(), null, newRoom);
        String roomString = converter.getAsString(FacesContext.getCurrentInstance(), null, room);
        if (!newRoomString.equals(roomString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
