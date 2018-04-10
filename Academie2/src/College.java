import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("b83b92c6-8a06-48cc-bb83-b040d1353c22")
public class College {
    @objid ("bfedad6e-6b91-46ae-a9b6-d5827a105ab5")
    public String nom;

    @objid ("ef9400dd-04a4-418d-a6aa-572a0601ae98")
    public int numeroAcademique;

    @objid ("1f238940-e0fa-410e-af77-b9f6af678520")
    public String site internet;

    @objid ("0e07d654-9214-4cd8-8641-f27aed0ab3a6")
    public List<Departement> departement = new ArrayList<Departement> ();

    @objid ("54064a61-810d-4334-bf9d-b499d389e380")
    public Adresse adresse;

    @objid ("77a88df7-2ebd-488c-97d7-1873d5b7ae36")
    public College() {
    }

}
