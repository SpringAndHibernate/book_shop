package by.bsuir.book_shop.service;

import by.bsuir.book_shop.dao.CategoryRepository;
import by.bsuir.book_shop.entity.Book;
import by.bsuir.book_shop.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(Long id){
        return categoryRepository.findById(id);
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Category category){

        categoryRepository.updateCategory(category.getName(),category.getId());
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
