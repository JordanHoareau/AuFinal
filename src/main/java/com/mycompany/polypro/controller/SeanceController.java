package com.mycompany.polypro.controller;

import com.mycompany.polypro.model.Seance;
import com.mycompany.polypro.business.SeanceEJB;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.ListDataModel;


@ManagedBean
@SessionScoped
public class SeanceController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private SeanceEJB seanceEJB;

    private HtmlDataTable dataTable;

    private Seance seance = new Seance();
    private ListDataModel seanceList; 

    private void updateSeanceList() {
        ArrayList<Seance> list = new ArrayList<Seance>();
        list.addAll(seanceEJB.findAll());
        seanceList = new ListDataModel(list);
    }

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doNew() {
        return "createSeance.xhtml";
    }

    public String doCreate() {
        seance = seanceEJB.create(seance);
        updateSeanceList();
        seance = new Seance();
        return "listSeances.xhtml";
    }
    
    public String doDelete() {
        List<Seance> seances = (List<Seance>)seanceList.getWrappedData();
        seanceEJB.delete(onlySelected(seances));
        updateSeanceList();
        return "listSeances.xhtml";
    }

    private List<Seance> onlySelected(List<Seance> list) {
        for (Iterator<Seance> it = list.iterator(); it.hasNext(); ) {
            if (!(it.next().isSelected()))
                it.remove();
        }
        return list;
    }

    public String doEdit() {
        seance = (Seance) seanceList.getRowData(); // Voici comment on trouve la s�ance sélectionné
        return "editSeance.xhtml";
    }

    public String doSave() {
        seanceEJB.update(seance);
        updateSeanceList();
        seance = new Seance();
        return "listSeances.xhtml";
    }
    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance s) {
        this.seance = s;
    }

    public ListDataModel getSeanceList() {
        updateSeanceList();
        return seanceList;
    }

    public void setSeanceList(ListDataModel seanceList) {
        this.seanceList = seanceList;
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    
}