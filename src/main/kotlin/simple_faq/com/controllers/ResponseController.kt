package simple_faq.com.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import simple_faq.com.domain.Response
import simple_faq.com.domain.ports.interfaces.ResponseServicePort

@Controller
@RequestMapping("/api/response")
class ResponseController(private var service: ResponseServicePort) {
    @GetMapping
    fun findAll(): ResponseEntity<List<Response>> {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Response> {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    fun create(@RequestBody response: Response): ResponseEntity<Response> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(response));
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody response: Response): ResponseEntity<Response> {
        return ResponseEntity.ok().body(service.update(id, response));
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        return ResponseEntity.ok(service.deleteById(id));
    }
}