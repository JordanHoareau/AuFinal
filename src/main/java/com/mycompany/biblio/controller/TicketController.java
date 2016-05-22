package com.mycompany.biblio.controller;

import com.mycompany.polypro.model.Ticket;
import com.mycompany.polypro.business.TicketEJB;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.ListDataModel;


@ManagedBean
@SessionScoped
public class TicketController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private TicketEJB ticketEJB;

    private HtmlDataTable dataTable;

    private Ticket ticket = new Ticket();
    private ListDataModel ticketList;

    private void updateTicketList() {
        ticketList = new ListDataModel(ticketEJB.findAll());
    }

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doNew() {
        return "createTicket.xhtml";
    }

    public String doCreate() {
        ticket = ticketEJB.create(ticket);
        updateTicketList();
        ticket = new Ticket();
        return "listTickets.xhtml";
    }
    
    public String doDelete() {
        List<Ticket> tickets = (List<Ticket>)ticketList.getWrappedData();
        ticketEJB.delete(onlySelected(tickets));
        updateTicketList();
        return "listTickets.xhtml";
    }

    private List<Ticket> onlySelected(List<Ticket> list) {
        for (Iterator<Ticket> it = list.iterator(); it.hasNext(); ) {
            if (!(it.next().isSelected()))
                it.remove();
        }
        return list;
    }

    public String doEdit() {
        ticket = (Ticket) ticketList.getRowData(); // Voici comment on trouve le contact sélectionné
        return "editTicket.xhtml";
    }

    public String doSave() {
        ticketEJB.update(ticket);
        updateTicketList();
        ticket = new Ticket();
        return "listTickets.xhtml";
    }
    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket t) {
        this.ticket = t;
    }

    public ListDataModel getTicketList() {
        updateTicketList();
        return ticketList;
    }

    public void setTicketList(ListDataModel ticketList) {
        this.ticketList = ticketList;
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    
}