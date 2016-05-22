package com.mycompany.biblio.controller;

import com.mycompany.polypro.business.ContactEJB;
import com.mycompany.polypro.model.Contact;
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
public class ContactController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private ContactEJB contactEJB;

    private HtmlDataTable dataTable;

    private Contact contact = new Contact();
    private ListDataModel contactList; // j'ai utilisé un ListDataModel et pas List parce que cela permet de retrouver l'élément sélectionné dans la liste (pour l'édition d'un livre)

    private void updateContactList() {
        contactList = new ListDataModel(contactEJB.findAll());
    }

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doNew() {
        return "createContact.xhtml";
    }

    public String doCreate() {
        contact = contactEJB.create(contact);
        updateContactList();
        return "listContacts.xhtml";
    }
    
    public String doDelete() {
        List<Contact> contacts = (List<Contact>)contactList.getWrappedData();
        contactEJB.delete(onlySelected(contacts));
        updateContactList();
        return "listContacts.xhtml";
    }

    private List<Contact> onlySelected(List<Contact> list) {
        for (Iterator<Contact> it = list.iterator(); it.hasNext(); ) {
            if (!(it.next().isSelected()))
                it.remove();
        }
        return list;
    }

    public String doEdit() {
        contact = (Contact) contactList.getRowData(); // Voici comment on trouve le contact sélectionné
        return "editContact.xhtml";
    }

    public String doSave() {
        // ajouter ce qui manque ici
        return "listContacts.xhtml";
    }
    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact c) {
        this.contact = c;
    }

    public ListDataModel getContactList() {
        updateContactList();
        return contactList;
    }

    public void setContactList(ListDataModel contactList) {
        this.contactList = contactList;
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    
}