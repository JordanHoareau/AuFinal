package af.AuFinal.Model;

import af.AuFinal.Model.Seance;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-11T17:10:08")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, Integer> isrefundable;
    public static volatile SingularAttribute<Ticket, Seance> seanceid;
    public static volatile SingularAttribute<Ticket, Integer> ticketprice;
    public static volatile SingularAttribute<Ticket, Integer> ticketid;

}