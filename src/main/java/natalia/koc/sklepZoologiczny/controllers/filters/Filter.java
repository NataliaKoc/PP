package natalia.koc.sklepZoologiczny.controllers.filters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import natalia.koc.sklepZoologiczny.domain.Kategoria;
import natalia.koc.sklepZoologiczny.domain.Zwierzeta;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class Filter {
    private String phrase = "";
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private Float minCena;
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private Float maxCena;
    private List<Zwierzeta> zwierzeta = new ArrayList<>();
    private List<Kategoria> kategoria = new ArrayList<>();
    public QUERY_MODE QueryMode = QUERY_MODE.NAMED_METHOD;

    public boolean isEmpty() {
        return StringUtils.isEmpty(phrase) && minCena == null && maxCena == null;
    }

    public String getPhraseLIKE() {
        return StringUtils.isEmpty(phrase)?"": "%"+phrase+"%";
    }

    public void clear() {
        phrase = "";
        maxCena = null;
        minCena = null;
    }
    public enum QUERY_MODE{
        NAMED_METHOD,
        NAMED_QUERY,
        QUERY,
        ENTITY_SpEL_AND_GRAPH,
        CRITERIA
    }
    public boolean isZwierzetaEmpty() {
        return zwierzeta.isEmpty();
    }
    public boolean isKategoriaEmpty() {
        return kategoria.isEmpty();
    }
}
