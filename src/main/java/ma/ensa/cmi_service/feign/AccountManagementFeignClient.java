package ma.ensa.cmi_service.feign;

import ma.ensa.cmi_service.dtos.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accountManagement-service")
public interface AccountManagementFeignClient {

    @GetMapping("/api/client/{id}")
    ClientDTO getClientById(@PathVariable("id") Long id);

    @PutMapping("/api/client/{id}/saveToken")
    void updateClientSaveTokenById(@PathVariable Long id, @RequestBody String saveToken);
}
