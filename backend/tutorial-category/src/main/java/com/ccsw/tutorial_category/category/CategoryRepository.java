package com.ccsw.tutorial_category.category;

import com.ccsw.tutorial_category.category.model.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * @author ccsw
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
