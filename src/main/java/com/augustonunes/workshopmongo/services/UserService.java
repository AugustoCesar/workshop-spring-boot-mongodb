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

    public User insert(User obj) {
        return userRepository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj) {
        Optional<User> objOpt = userRepository.findById(obj.getId());
        if (objOpt.isEmpty()) {
            throw new ObjectNotFoundException("Object not found");
        }
        updateData(objOpt.get(), obj);
        return userRepository.save(objOpt.get());
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
