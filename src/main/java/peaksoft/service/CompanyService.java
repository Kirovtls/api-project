package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.responseView.CompanyResponseView;
import peaksoft.exception.NotFoundException;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyResponse addCompany(CompanyRequest request) {
        Company company = createCompany(request);
        companyRepository.save(company);
        return getResponse(company);

    }

    public CompanyResponse updateCompany(Long id, CompanyRequest request) {
        Company company = getCompanyById(id);
        Company company1 = updateCompany(company, request);
        companyRepository.save(company1);
        return getResponse(company1);
    }

    public CompanyResponse getById(Long id) {
        Company company = getCompanyById(id);
        return getResponse(company);
    }

    public CompanyResponse block(Long id) {
        Company company = getCompanyById(id);
        company.setActive(false);
        companyRepository.save(company);
        return getResponse(company);

    }

    public SimpleResponse deleteById(Long companyId) {
        boolean exists = companyRepository.existsById(companyId);
        if (!exists) {
            throw new NotFoundException("instructor with id " + companyId + " not found!");
        }
        companyRepository.deleteById(companyId);
        return new SimpleResponse(
                "DELETED",
                "company with id " + companyId + "deleted successfully"
        );
    }

    private Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(() ->
                new NotFoundException("Company with id: " + companyId + "not found !"));
    }

    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    public CompanyResponseView getAllCompaniesPagination(String text, int page, int size) {
        CompanyResponseView responseView = new CompanyResponseView();
        Pageable pageable = PageRequest.of(page - 1, size);
        responseView.setCompanyResponses(getCompanies(search(text, pageable)));
        return responseView;
    }

    public List<CompanyResponse> getCompanies(List<Company> companies) {
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company : companies) {
            responses.add(getResponse(company));
        }
        return responses;
    }

    private List<Company> search(String name, Pageable pageable) {
        String text = name == null ? "" : name;
        return companyRepository.searchCompanyByCompanyName(text.toUpperCase(Locale.ROOT), pageable);
    }

    public Company createCompany(CompanyRequest request) {
        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        company.setActive(company.isActive());
        company.setCreateAt(LocalDate.now());
        return company;
    }

    public Company updateCompany(Company company, CompanyRequest request) {
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        company.setActive(company.isActive());
        company.setCreateAt(LocalDate.now());
        return company;
    }

    public CompanyResponse getResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setCompanyId(company.getCompanyId());
        companyResponse.setActive(company.isActive());
        companyResponse.setCreateAt(LocalDate.now());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setLocatedCountry(company.getLocatedCountry());
        return companyResponse;

    }
}