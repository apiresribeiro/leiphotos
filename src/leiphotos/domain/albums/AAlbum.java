package leiphotos.domain.albums;

public abstract class AAlbum implements IAlbum {
    List<IPhoto> photosAlbum;
    
    @Override
    public int numberOfPhotos(){
        return this.photosAlbum.size(); 
    };

    @Override
	public String getName(){
        
    }

    @Override
	public List<IPhoto> getPhotos(){
        return this.photosAlbum;
    }

    @Override
	public boolean addPhotos(Set<IPhoto> photos){

    }

	@Override
	public boolean removePhotos(Set<IPhoto> photos){

    }

	@Override
	public void processEvent(PhotoLibraryEvent e){

    }
}
