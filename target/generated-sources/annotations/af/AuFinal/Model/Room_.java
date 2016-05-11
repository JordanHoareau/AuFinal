package af.AuFinal.Model;

import af.AuFinal.Model.Seance;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-11T17:10:08")
@StaticMetamodel(Room.class)
public class Room_ { 

    public static volatile SingularAttribute<Room, Integer> roomid;
    public static volatile SingularAttribute<Room, Integer> maxplaces;
    public static volatile CollectionAttribute<Room, Seance> seanceCollection;
    public static volatile SingularAttribute<Room, Integer> roomprice;

}