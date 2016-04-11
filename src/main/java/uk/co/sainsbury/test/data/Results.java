package uk.co.sainsbury.test.data;

import java.util.List;

/**
 *
 */
public class Results
{
    private List<ProductData> results;

    private String total;


    public String getTotal() {
        return total;
    }

    public void setTotal(final String total) {
        this.total = total;
    }

    public List<ProductData> getResults() {
        return results;
    }

    public void setResults(final List<ProductData> results) {
        this.results = results;
    }
}
