package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.responseView.CompanyResponseView;
import peaksoft.model.Company;
import peaksoft.service.CompanyService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService service;

    @PostMapping
    public CompanyResponse create(@RequestBody CompanyRequest company) {
        return service.addCompany(company);
    }

    @GetMapping("/{id}")
    public CompanyResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public CompanyResponse deleteById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/block/{id}")
    public CompanyResponse blockStudent(@PathVariable Long id) {
        return service.block(id);

    }

    @PutMapping("/{id}")
    public CompanyResponse update(@PathVariable Long id, @RequestBody CompanyRequest request) {
        return service.updateCompany(id, request);
    }

    @GetMapping
    public List<Company> getAll() {
        return service.findAllCompany();
    }

    public CompanyResponseView getAllPagination(@RequestParam(name = "text", required = false) String text,
                                                @RequestParam int page,
                                                @RequestParam int size) {
        return service.getAllCompaniesPagination(text, page, size);

    }


}
