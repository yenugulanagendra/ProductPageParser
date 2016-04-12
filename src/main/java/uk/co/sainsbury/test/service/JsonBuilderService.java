package uk.co.sainsbury.test.service;

import uk.co.sainsbury.test.data.ProductData;

import java.util.List;

/**
 * Json Builder Service
 */
public interface JsonBuilderService
{
    String buildJsonObject(List<ProductData> productDatas);
}
