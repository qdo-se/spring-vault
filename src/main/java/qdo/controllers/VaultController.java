package qdo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/vault")
@RestController
public class VaultController {

    VaultTemplate vaultTemplate;

    @Autowired
    public VaultController(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    /**
     * Get all questions
     *
     * @return List of Question objects
     */
    @GetMapping
    public String getSecrets() {
        String result = "";

        VaultResponse response = vaultTemplate.read("secret/data/hello-world");

        Map<String, String> data = (Map<String, String>) response.getRequiredData().get("data");

        for(Map.Entry<String, String> entry: data.entrySet()) {
            result += entry.getKey() + ": " + entry.getValue() + "\n";
        }

        return result;
    }
}
