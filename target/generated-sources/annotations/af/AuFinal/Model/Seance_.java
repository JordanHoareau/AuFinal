package af.AuFinal.Model;

import af.AuFinal.Model.Movie;
import af.AuFinal.Model.Room;
import af.AuFinal.Model.Ticket;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-11T17:10:08")
@StaticMetamodel(Seance.class)
public class Seance_ { 

    public static volatile SingularAttribute<Seance, Room> roomid;
    public static volatile SingularAttribute<Seance, Integer> remainingplaces;
    public static volatile SingularAttribute<Seance, Integer> seanceid;
    public static volatile SingularAttribute<Seance, Integer> dayofweek;
    public static volatile CollectionAttribute<Seance, Ticket> ticketCollection;
    public static volatile SingularAttribute<Seance, Movie> movieid;

}