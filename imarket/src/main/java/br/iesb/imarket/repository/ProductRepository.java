package br.iesb.imarket.repository;

import br.iesb.imarket.exception.ProductNotFoundException;
import br.iesb.imarket.model.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ProductRepository {
    private List<ProductEntity> bankSimulator = new ArrayList<>();

    public List<ProductEntity> getProducts(){
        return bankSimulator;
    }

    public List<ProductEntity> getProductsCategory(String category){
        List<ProductEntity> productsCategory = new ArrayList<>();
        for(ProductEntity aux : bankSimulator){
            if(aux.getCategory().equals(category)){
                productsCategory.add(aux);
            }
        }
        return productsCategory;
    }

    public List<ProductEntity> getProductsBrand(String brand){
        List<ProductEntity> productsBrand = new ArrayList<>();
        for(ProductEntity aux : bankSimulator){
            if(aux.getBrand().equals(brand)){
                productsBrand.add(aux);
            }
        }
        return productsBrand;
    }

    public List<ProductEntity> getProductsPromotion(){
        List<ProductEntity> productsPromotion = new ArrayList<>();
        for(ProductEntity aux : bankSimulator){
            if(aux.isPromotion()){
                productsPromotion.add(aux);
            }
        }
        return productsPromotion;
    }

    public List<ProductEntity> getProductsCrescente(){
        List<ProductEntity> productsCrescente = new ArrayList<>(bankSimulator);
        quickSort(productsCrescente,0,productsCrescente.size()-1);
        return productsCrescente;
    }

    private void quickSort(List<ProductEntity> vet, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vet, inicio, fim);
            quickSort(vet, inicio, posicaoPivo - 1);
            quickSort(vet, posicaoPivo + 1, fim);
        }
    }
    private int separar(List<ProductEntity> vet, int inicio, int fim) {
        int pivor,aux;
        pivor = inicio;
        aux = fim;

        while(pivor != aux){
            if(pivor < aux){
                if(vet.get(pivor).getPriceDto() > vet.get(aux).getPriceDto()){
                    swap(vet,pivor,aux);
                }else{
                    aux--;
                }
            }else{
                if(vet.get(pivor).getPriceDto() < vet.get(aux).getPriceDto()){
                    swap(vet,pivor,aux);
                }else{
                    aux++;
                }
            }
        }
        return pivor;
    }

    private void swap(List<ProductEntity> vet, int pivor, int aux){
        ProductEntity productAux = new ProductEntity();
        productAux.setName(vet.get(pivor).getName());
        productAux.setBrand(vet.get(pivor).getBrand());
        productAux.setId(vet.get(pivor).getId());
        productAux.setCategory(vet.get(pivor).getCategory());
        productAux.setCreate(vet.get(pivor).getCreate());
        productAux.setUpdate(vet.get(pivor).getUpdate());
        productAux.setDescription(vet.get(pivor).getDescription());
        productAux.setQuantity(vet.get(pivor).getQuantity());
        productAux.setPrice(vet.get(pivor).getPrice());
        productAux.setPromotion(vet.get(pivor).isPromotion());
        productAux.setPercent(vet.get(pivor).getPercent());

        vet.get(pivor).setName(vet.get(aux).getName());
        vet.get(pivor).setBrand(vet.get(aux).getBrand());
        vet.get(pivor).setId(vet.get(aux).getId());
        vet.get(pivor).setCategory(vet.get(aux).getCategory());
        vet.get(pivor).setCreate(vet.get(aux).getCreate());
        vet.get(pivor).setUpdate(vet.get(aux).getUpdate());
        vet.get(pivor).setDescription(vet.get(aux).getDescription());
        vet.get(pivor).setQuantity(vet.get(aux).getQuantity());
        vet.get(pivor).setPrice(vet.get(aux).getPrice());
        vet.get(pivor).setPromotion(vet.get(aux).isPromotion());
        vet.get(pivor).setPercent(vet.get(aux).getPercent());

        vet.get(aux).setName(productAux.getName());
        vet.get(aux).setBrand(productAux.getBrand());
        vet.get(aux).setId(productAux.getId());
        vet.get(aux).setCategory(productAux.getCategory());
        vet.get(aux).setCreate(productAux.getCreate());
        vet.get(aux).setUpdate(productAux.getUpdate());
        vet.get(aux).setDescription(productAux.getDescription());
        vet.get(aux).setQuantity(productAux.getQuantity());
        vet.get(aux).setPrice(productAux.getPrice());
        vet.get(aux).setPromotion(productAux.isPromotion());
        vet.get(aux).setPercent(productAux.getPercent());
    }
    public void saveProduct(ProductEntity product){
        if(bankSimulator.size() == 0){
            product.setId(1);
        }else {
            product.setId(bankSimulator.get(bankSimulator.size() - 1).getId() + 1);
        }
        bankSimulator.add(product);
    }

    public int updateProduct(long id, ProductEntity product){
        int aux = -1;

        for(ProductEntity productAux: bankSimulator){
            if(productAux.getId() == id) {
                productAux.setName(product.getName());
                productAux.setBrand(product.getBrand());
                productAux.setDescription(product.getDescription());
                productAux.setQuantity(product.getQuantity());
                productAux.setCategory(product.getCategory());
                productAux.setPromotion(product.isPromotion());
                if (product.isPromotion() == true) {
                    productAux.setPercent(product.getPercent());
                }
                productAux.setPrice(product.getPrice());
                Date data = new Date();
                productAux.setUpdate(data);
                aux = 0;
                return aux;
            }
        }
        return aux;
    }

    public int updatePromotionProductCategory(String category, float percent){
        int verificador = -1;
        for(ProductEntity aux: bankSimulator){
            if(aux.getCategory().equals(category)){
                aux.setPromotion(true);
                aux.setPercent(percent);
                Date data = new Date();
                aux.setUpdate(data);
                verificador = 0;
            }
        }
        return verificador;
    }

    public int updatePromotionProductBrand(String brand, float percent){
        int verificador = -1;
        for(ProductEntity aux: bankSimulator){
            if(aux.getBrand().equals(brand)){
                aux.setPromotion(true);
                aux.setPercent(percent);
                Date data = new Date();
                aux.setUpdate(data);
                verificador = 0;
            }
        }
        return verificador;
    }

    public int updatePromotionProduct(float percent){
        int verificador = -1;
        for(ProductEntity aux: bankSimulator){
            aux.setPromotion(true);
            aux.setPercent(percent);
            Date data = new Date();
            aux.setUpdate(data);
            verificador = 0;
        }
        return verificador;
    }

    public int deleteProduct(long id) throws ProductNotFoundException {
        for(int i = 0; i <= bankSimulator.size()-1; i++){
            if(bankSimulator.get(i).getId() == id){
                bankSimulator.remove(i);
                return 0;
            }
        }
        return 1;
    }

    public int deleteProductCategory(String category){
        int aux = 0;
        for(int i = 0; i <= bankSimulator.size()-1; i++){
            if(bankSimulator.get(i).getCategory().equals(category)){
                bankSimulator.remove(i);
                aux = 1;
            }
        }
        return aux;
    }

    public int deleteProductBrand(String brand) {
        int aux = 0;
        for (int i = 0; i <= bankSimulator.size() - 1; i++) {
            if (bankSimulator.get(i).getBrand().equals(brand)) {
                bankSimulator.remove(i);
                aux = 1;
            }
        }
        return aux;
    }

    public void deleteAll(){

        bankSimulator.clear();
    }
}