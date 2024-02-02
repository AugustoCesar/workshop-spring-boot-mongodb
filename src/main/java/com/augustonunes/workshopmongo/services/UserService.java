package com.augustonunes.workshopmongo.services;

import com.augustonunes.workshopmongo.domain.User;
import com.augustonunes.workshopmongo.dto.UserDTO;
import com.augustonunes.workshopmongo.repositories.UserRepository;
import com.augustonunes.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new ObjectNotFoundException("Object not found");
        }
        return userOpt.get();
    }

    public User insert(User obj){
        return userRepository.insert(obj);
    }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
