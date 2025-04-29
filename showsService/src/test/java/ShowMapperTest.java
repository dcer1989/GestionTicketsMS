import com.hiberus.dto.ShowResponse;
import com.hiberus.mapper.ShowMapper;
import com.hiberus.model.Show;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowMapperTest {

    private final ShowMapper showMapper = Mappers.getMapper(ShowMapper.class);

    @Test
    void testToDto() {
        Show show = new Show();
        show.setId(UUID.randomUUID());
        show.setTitle("Test Show");
        show.setDescription("Test Description");

        ShowResponse showResponse = showMapper.toDto(show);

        assertEquals(show.getId(), showResponse.id());
        assertEquals(show.getTitle(), showResponse.title());
        assertEquals(show.getDescription(), showResponse.description());
    }
}
