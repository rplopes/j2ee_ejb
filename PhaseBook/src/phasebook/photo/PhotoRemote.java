package phasebook.photo;
import javax.ejb.Remote;

@Remote
public interface PhotoRemote {
	
	public Photo getPhotoById(String id);

}
