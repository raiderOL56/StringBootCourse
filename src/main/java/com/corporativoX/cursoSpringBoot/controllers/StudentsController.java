package com.corporativoX.cursoSpringBoot.controllers;

import com.corporativoX.cursoSpringBoot.models.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RestController
@RequestMapping("/students")
public class StudentsController {
    private List<Student> _students = new ArrayList<>(Arrays.asList(
            new Student(1, "Erick", 27, "erick@outlook.com", "Informática"),
            new Student(2, "Vannia", 31, "vannia@outlook.com", "Administración")
    ));

    @GetMapping
    public ResponseEntity<List<Student>> allStudents(){
        return ResponseEntity.ok(_students);
    }

    @GetMapping("byEmail/")
    public ResponseEntity<Student> getByEmail(@RequestParam String email){
        Student student = _students.stream().filter(s -> s.getEmail().equals(email)).findFirst().orElse(null);

        if (student == null ) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<List<Student>> addStudent(@RequestBody Student student){
        _students.add(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // obtiene información de la URI
                .path("/{email}")
                .buildAndExpand(student.getEmail()) // toma el valor proporcionado en la línea anterior y lo inserta en el segmento de ruta. Resultado: "http://localhost:1234/students/alejandro@outlook.com"
                .toUri();

        return ResponseEntity.created(location).body(_students);
    }

    @PutMapping
    public ResponseEntity<List<Student>> updateStudent(@RequestBody Student student){
        Student entity = _students.stream().filter(s -> s.getId() == student.getId()).findFirst().orElse(null);

        if (entity == null) return ResponseEntity.notFound().build();

        entity.setName(student.getName());
        entity.setAge(student.getAge());
        entity.setEmail(student.getEmail());
        entity.setCourse(student.getCourse());

        return ResponseEntity.ok(_students);
    }

    @PatchMapping
    public ResponseEntity<List<Student>> patchStudent(@RequestBody Student student){
        Student entity = _students.stream().filter(s -> s.getId() == student.getId()).findFirst().orElse(null);

        if (entity == null) return ResponseEntity.notFound().build();

        if (hasText(student.getName())) entity.setName(student.getName());
        if (student.getAge() != 0) entity.setAge(student.getAge());
        if (hasText(student.getEmail())) entity.setEmail(student.getEmail());
        if (hasText(student.getCourse())) entity.setCourse(student.getCourse());

        return ResponseEntity.ok(_students);
    }

    @DeleteMapping
    public ResponseEntity<List<Student>> deleteStudent (@RequestParam int id){
        Student entity = _students.stream().filter(student -> student.getId() == id).findFirst().orElse(null);

        if (entity == null) return ResponseEntity.notFound().build();

        _students.remove(entity);

        return ResponseEntity.ok(_students);
    }
}
