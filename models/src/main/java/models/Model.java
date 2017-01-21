package models;

import java.util.List;

public interface Model {
    double predict(Object values) throws Exception;

    void predict(String inpath, String outpath, boolean header) throws Exception;

    void export(String path);

    void load(String path);

    void build();
}
