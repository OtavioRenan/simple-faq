package simple_faq.com.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import simple_faq.com.domain.Doubt
import simple_faq.com.domain.dtos.DoubtDTO
import simple_faq.com.domain.ports.interfaces.DoubtAndResponseServicePort
import simple_faq.com.domain.ports.interfaces.DoubtServicePort

@Controller
@RequestMapping("/api/doubt")
class DoubtController(
    private var service: DoubtServicePort,
    private var doubtAndResponseService: DoubtAndResponseServicePort
) {
    @GetMapping
    fun findAll(@RequestParam inputs: Map<String, String>): ResponseEntity<List<DoubtDTO>> {
        return ResponseEntity.ok().body(doubtAndResponseService.findAll(inputs));
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Doubt> {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    fun create(@RequestBody doubt: Doubt): ResponseEntity<Doubt> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(doubt));
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody doubt: Doubt): ResponseEntity<Doubt> {
        return ResponseEntity.ok().body(service.update(id, doubt));
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any> {
        return ResponseEntity.ok(service.deleteById(id));
    }
}