package by.bsuir.book_shop.service;

import by.bsuir.book_shop.dao.PublishingRepository;
import by.bsuir.book_shop.entity.Publishing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublishingService {

    private final PublishingRepository publishingRepository;

    @Autowired
    public PublishingService(PublishingRepository publishingRepository) {
        this.publishingRepository = publishingRepository;
    }

    public List<Publishing> getAllPublishings(){
        return publishingRepository.findAll();
    }

    public Optional<Publishing> getPublishing(Long id){
        return publishingRepository.findById(id);
    }

    public void addPublishing(Publishing publishing){
        publishingRepository.save(publishing);
    }

    public void updatePublishing(Publishing publishing){

        publishingRepository.updatePublishing(publishing.getName(),publishing.getWebsite(),
                publishing.getPhone(),publishing.getAddress().getCity(),publishing.getAddress().getStreet(),
                publishing.getAddress().getBuilding(), publishing.getAddress().getPostalCode(),publishing.getId());
    }

    public void deletePublishing(Long id){
        publishingRepository.deleteById(id);
    }
}
