package com.augustonunes.workshopmongo.services;

import com.augustonunes.workshopmongo.domain.Post;
import com.augustonunes.workshopmongo.repositories.PostRepository;
import com.augustonunes.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public Post findById(String id) {
        Optional<Post> postOpt = postRepository.findById(id);
        return postOpt.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public List<Post> findByTitle(String text) {
        return postRepository.searchTitle(text);
    }

}
