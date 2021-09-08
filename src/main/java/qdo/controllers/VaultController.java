package qdo.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qdo.models.Secret;
import qdo.models.Vault;

@RequestMapping({"api/v1/vault", ""})
@RestController
public class VaultController {

    Logger logger = LoggerFactory.getLogger(VaultController.class);

    VaultTemplate vaultTemplate;

    @Value("${vault.uri:http://127.0.0.1:8200}")
    URI vaultUri;

    @Value("${vault.app-role.role-id:dummy-role}")
    String roleId;

    @Value("${vault.app-role.secret-id:dummy-secret}")
    String secretId;

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
    public Vault getSecrets() {
        VaultResponse response = vaultTemplate.read("secret/data/hello-world");

        Map<String, String> data = (Map<String, String>) response.getRequiredData().get("data");

        ArrayList<Secret> secretList = new ArrayList<>();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            secretList.add(new Secret(entry.getKey(), entry.getValue()));
        }

        Vault vault = new Vault(roleId, secretId, secretList);

        return vault;
    }
}
