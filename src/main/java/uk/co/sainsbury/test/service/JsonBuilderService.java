package uk.co.sainsbury.test.service;

import uk.co.sainsbury.test.data.ProductData;
import uk.co.sainsbury.test.data.Results;

import java.util.List;

/**
 * Json Builder Service
 */
public interface JsonBuilderService
{
    /**
     * Builds Json Object from Results
     * @param result
     * @return
     */
    String buildJsonObject(Results result);
}
