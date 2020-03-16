package com.example.demo.demo.web;

import com.example.demo.demo.Group;
import com.example.demo.demo.GroupRepositry;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

// add "" @CrossOrigin(origins = "http://localhost:3000") "" to enable CORS (to allow access for all the routing/paths). http://localhost:3000 ==> the address where your react page is up.

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")

class GroupController {
    private final Logger log = LoggerFactory.getLogger(GroupController.class);
    private GroupRepositry groupRepositry;

    public  GroupController(GroupRepositry groupRepositry){
        this.groupRepositry = groupRepositry;
    }

    @GetMapping("/groups")
    Collection<Group> groups(){
        return  groupRepositry.findAll();
    }

    @GetMapping("/group/{id}")
    ResponseEntity<?> getGroup(@PathVariable Long id){
        Optional<Group> group= groupRepositry.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/group")
    ResponseEntity<Group> createGroup(@Valid @RequestBody Group group) throws URISyntaxException{
        log.info("Request to create group: {}", group);
        Group result = groupRepositry.save(group);

        return ResponseEntity.created(new URI("/api/group" + result.getId())).body(result);
    }

    @PutMapping("/group/{id}")
    ResponseEntity<Group> updateGroup(@Valid @RequestBody Group group){
        log.info("Request to update group: {}", group);
        Group result = groupRepositry.save(group);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/group/{id}")
        public ResponseEntity<?> deleteGroup(@PathVariable Long id){
            log.info("Request to delete group: {}" , id);
            groupRepositry.deleteById(id);
            return ResponseEntity.ok().build();

    }
}
