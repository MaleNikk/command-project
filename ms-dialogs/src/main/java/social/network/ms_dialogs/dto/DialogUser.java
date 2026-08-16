package social.network.ms_dialogs.dto;

import java.util.Collection;

public record DialogUser(String id, String username, String password, String firstName, String lastName,
                         boolean deleted, Collection<Object> authorities) {
}
