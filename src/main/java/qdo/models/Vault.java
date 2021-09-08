package qdo.models;

import java.util.List;

public class Vault {
    private String vaultRoleId;
    private String vaultSecretId;
    private List<Secret> secrets;

    public Vault(String vaultRoleId, String vaultSecretId, List<Secret> secrets) {
        this.vaultRoleId = vaultRoleId;
        this.vaultSecretId = vaultSecretId;
        this.secrets = secrets;
    }

    public String getVaultRoleId() {
        return vaultRoleId;
    }

    public String getVaultSecretId() {
        return vaultSecretId;
    }

    public List<Secret> getSecrets() {
        return secrets;
    }
}
