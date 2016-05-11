package af.AuFinal.Model;

import af.AuFinal.Model.Seance;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-11T17:10:08")
@StaticMetamodel(Movie.class)
public class Movie_ { 

    public static volatile SingularAttribute<Movie, String> movietitle;
    public static volatile CollectionAttribute<Movie, Seance> seanceCollection;
    public static volatile SingularAttribute<Movie, String> moviedesc;
    public static volatile SingularAttribute<Movie, Integer> movieyear;
    public static volatile SingularAttribute<Movie, Integer> movieid;
    public static volatile SingularAttribute<Movie, Integer> movieprice;

}