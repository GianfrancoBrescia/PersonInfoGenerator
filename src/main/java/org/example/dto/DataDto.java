package org.example.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.example.utils.Utils;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;


public class DataDto extends TreeMap<String, DataDto.ElencoDto> {

    public DataDto() {
        populateMap("elenco_comuni.csv");
        populateMap("elenco_stati.csv");
    }

    @SneakyThrows
    private void populateMap(String fileName) {
        HeaderColumnNameMappingStrategy<ElencoDto> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
        mappingStrategy.setType(ElencoDto.class);

        String file = Objects.requireNonNull(DataDto.class.getClassLoader().getResource(fileName)).getFile();
        FileReader fileReader = new FileReader(file, StandardCharsets.UTF_8);

        CsvToBean<ElencoDto> csvToBean = new CsvToBeanBuilder<ElencoDto>(fileReader)
                .withType(ElencoDto.class)
                .withMappingStrategy(mappingStrategy)
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                .withThrowExceptions(true)
                .withSeparator(',')
                .build();

        this.putAll(
                csvToBean.parse()
                        .stream()
                        .collect(LinkedHashMap::new, (m, e) -> m.put(e.getCodiceBelfiore(), e), LinkedHashMap::putAll)
        );

        fileReader.close();
    }

    public String getKey(String valueToMatch) {
        return this.entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue().getDescrizione().toUpperCase(Locale.ROOT), valueToMatch.toUpperCase(Locale.ROOT)))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(Utils.ERROR);
    }

    public String getValueByKey(String key) {
        return this.get(key).getDescrizione();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ElencoDto {
        @CsvBindByName(column = "DESCRIZIONE")
        private String descrizione;

        @CsvBindByName(column = "SIGLA")
        private String sigla;

        @CsvBindByName(column = "CODICE BELFIORE")
        private String codiceBelfiore;
    }
}
