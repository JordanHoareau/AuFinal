package com.mycompany.polypro.controller;

import com.mycompany.polypro.business.FilmEJB;
import com.mycompany.polypro.model.Film;
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
public class FilmController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private FilmEJB filmEJB;

    private HtmlDataTable dataTable;

    private Film film = new Film();
    private ListDataModel filmList; // j'ai utilisé un ListDataModel et pas List parce que cela permet de retrouver l'élément sélectionné dans la liste (pour l'édition d'un livre)

    private void updateFilmList() {
        ArrayList<Film> list = new ArrayList<Film>();
        list.addAll(filmEJB.findAll());
        filmList = new ListDataModel(list);
    }

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doNew() {
        return "createFilm.xhtml";
    }

    public String doCreate() {
        film = filmEJB.create(film);
        updateFilmList();
        film = new Film();
        return "listFilms.xhtml";
    }
    
    public String doDelete() {
        List<Film> films = (List<Film>)filmList.getWrappedData();
        filmEJB.delete(onlySelected(films));
        updateFilmList();
        return "listFilms.xhtml";
    }

    private List<Film> onlySelected(List<Film> list) {
        for (Iterator<Film> it = list.iterator(); it.hasNext(); ) {
            if (!(it.next().isSelected()))
                it.remove();
        }
        return list;
    }

    public String doEdit() {
        film = (Film) filmList.getRowData(); // Voici comment on trouve le film sélectionné
        return "editFilm.xhtml";
    }

    public String doSave() {
        filmEJB.update(film);
        updateFilmList();
        film = new Film();
        return "listFilms.xhtml";
    }
    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film c) {
        this.film = c;
    }

    public ListDataModel getFilmList() {
        updateFilmList();
        return filmList;
    }

    public void setFilmList(ListDataModel filmList) {
        this.filmList = filmList;
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    
}