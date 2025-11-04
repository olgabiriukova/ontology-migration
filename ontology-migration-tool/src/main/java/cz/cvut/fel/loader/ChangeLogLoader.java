package cz.cvut.fel.loader;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import cz.cvut.fel.exceptions.ChangeLogValidationException;
import cz.cvut.fel.model.ChangeLog;
import cz.cvut.fel.utils.ChangeLogValidator;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ChangeLogLoader {
    private final ChangeLogValidator validator;
    public ChangeLogLoader() throws IOException {
        this.validator = new ChangeLogValidator();
    }
    public ChangeLog load(InputStream input) throws IOException, ChangeLogValidationException {
        byte[] data = input.readAllBytes();
        validator.validate(new ByteArrayInputStream(data));
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(new ByteArrayInputStream(data), ChangeLog.class);
    }
}