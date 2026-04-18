package leiphotos.domain.controllers;

import java.util.Comparator;
import java.util.List;

import leiphotos.domain.facade.IPhoto;
import leiphotos.domain.facade.IViewsController;
import leiphotos.domain.facade.ViewsType;
import leiphotos.domain.views.IViewsCatalog;

public class ViewsController implements IViewsController {

    IViewsCatalog viewsCatalog;

    public ViewsController (IViewsCatalog view){
        this.viewsCatalog = view; 
    }

    @Override
    public List<IPhoto> getPhotos(ViewsType viewType) {
        return this.viewsCatalog.getView(viewType).getPhotos();
    }

    @Override
    public List<IPhoto> getMatches(ViewsType viewType, String regexp) {
        return this.viewsCatalog.getView(viewType).getMatches(regexp);
    }

    @Override
    public void setSortingCriteria(ViewsType v, Comparator<IPhoto> criteria) {
        this.viewsCatalog.getView(v).setComparator(criteria);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***** VIEWS *****\n");
        
        sb.append("***** VIEW ALL_MAIN: ").append(getPhotos(ViewsType.ALL_MAIN).size()).append(" photos *****\n");
        for (IPhoto p : getPhotos(ViewsType.ALL_MAIN)) {
            sb.append(p.file().getName()).append("\n");
        }
        
        sb.append("***** VIEW ALL_TRASH: ").append(getPhotos(ViewsType.ALL_TRASH).size()).append(" photos *****\n");
        for (IPhoto p : getPhotos(ViewsType.ALL_TRASH)) {
            sb.append(p.file().getName()).append("\n");
        }
        
        sb.append("***** VIEW FAVOURITES: ").append(getPhotos(ViewsType.FAVOURITES).size()).append(" photos *****\n");
        for (IPhoto p : getPhotos(ViewsType.FAVOURITES)) {
            sb.append(p.file().getName()).append("\n");
        }
        
        sb.append("***** VIEW MOST_RECENT: ").append(getPhotos(ViewsType.MOST_RECENT).size()).append(" photos *****\n");
        for (IPhoto p : getPhotos(ViewsType.MOST_RECENT)) {
            sb.append(p.file().getName()).append("\n");
        }
        
        return sb.toString();
    }
};
