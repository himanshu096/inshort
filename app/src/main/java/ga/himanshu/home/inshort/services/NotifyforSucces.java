package ga.himanshu.home.inshort.services;

import java.util.List;

import ga.himanshu.home.inshort.model.Category;
import ga.himanshu.home.inshort.model.Stories;

/**
 * Created by Hemant Saini on 07-07-2016.
 */
public interface NotifyforSucces {
        public void notifyforCategories();
        public void notifyforCategories(List<Category> categoryList);
        public void notifyforStories();
        public void notifyforStories(List<Stories> storiesList) ;
}
