package zw.co.jugaad.presenter.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zw.co.jugaad.usecases.cousine.CousineRepository;
import zw.co.jugaad.usecases.cousine.GetAllCousinesUseCase;
import zw.co.jugaad.usecases.cousine.GetStoresByCousineUseCase;
import zw.co.jugaad.usecases.cousine.SearchCousineByNameUseCase;
import zw.co.jugaad.usecases.customer.CreateCustomerUseCase;
import zw.co.jugaad.usecases.customer.CustomerRepository;
import zw.co.jugaad.usecases.order.CreateOrderUseCase;
import zw.co.jugaad.usecases.order.DeleteOrderUseCase;
import zw.co.jugaad.usecases.order.DeliveryOrderUseCase;
import zw.co.jugaad.usecases.order.GetCustomerOrderUseCase;
import zw.co.jugaad.usecases.order.GetOrderUseCase;
import zw.co.jugaad.usecases.order.OrderRepository;
import zw.co.jugaad.usecases.order.PayOrderUseCase;
import zw.co.jugaad.usecases.product.GetAllProductsUseCase;
import zw.co.jugaad.usecases.product.GetProductUseCase;
import zw.co.jugaad.usecases.product.GetProductsByStoreAndProductsIdUseCase;
import zw.co.jugaad.usecases.product.ProductRepository;
import zw.co.jugaad.usecases.product.SearchProductsByNameOrDescriptionUseCase;
import zw.co.jugaad.usecases.store.GetAllStoresUseCase;
import zw.co.jugaad.usecases.store.GetProductsByStoreUseCase;
import zw.co.jugaad.usecases.store.GetStoreUseCase;
import zw.co.jugaad.usecases.store.SearchStoresByNameUseCase;
import zw.co.jugaad.usecases.store.StoreRepository;


@Configuration
public class Module {

    @Bean
    public DeliveryOrderUseCase deliveryOrderUseCase(OrderRepository repository) {
        return new DeliveryOrderUseCase(repository);
    }

    @Bean
    public PayOrderUseCase payOrderUseCase(OrderRepository repository) {
        return new PayOrderUseCase(repository);
    }

    @Bean
    public DeleteOrderUseCase deleteOrderUseCase(OrderRepository repository) {
        return new DeleteOrderUseCase(repository);
    }

    @Bean
    public GetCustomerOrderUseCase getCustomerOrderUseCase(GetOrderUseCase getOrderUseCase) {
        return new GetCustomerOrderUseCase(getOrderUseCase);
    }

    @Bean
    public GetOrderUseCase getOrderUseCase(OrderRepository repository) {
        return new GetOrderUseCase(repository);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase(GetProductsByStoreAndProductsIdUseCase getProductsByStoreAndProductsIdUseCase,
                                                 OrderRepository repository) {
        return new CreateOrderUseCase(getProductsByStoreAndProductsIdUseCase, repository);
    }

    @Bean
    public GetProductsByStoreAndProductsIdUseCase getProductsByStoreAndProductsIdUseCase(ProductRepository repository) {
        return new GetProductsByStoreAndProductsIdUseCase(repository);
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerRepository repository) {
        return new CreateCustomerUseCase(repository);
    }

    @Bean
    public SearchProductsByNameOrDescriptionUseCase searchProductsByNameUseCase(ProductRepository repository) {
        return new SearchProductsByNameOrDescriptionUseCase(repository);
    }

    @Bean
    public GetProductUseCase getProductUseCase(ProductRepository repository) {
        return new GetProductUseCase(repository);
    }

    @Bean
    public GetAllProductsUseCase getAllProductsUseCase(ProductRepository repository) {
        return new GetAllProductsUseCase(repository);
    }

    @Bean
    public GetProductsByStoreUseCase getProductsByStoreIdentityUseCase(StoreRepository repository) {
        return new GetProductsByStoreUseCase(repository);
    }

    @Bean
    public GetStoreUseCase getStoreUseCase(StoreRepository repository) {
        return new GetStoreUseCase(repository);
    }

    @Bean
    public SearchStoresByNameUseCase searchStoresByNameUseCase(StoreRepository repository) {
        return new SearchStoresByNameUseCase(repository);
    }

    @Bean
    public GetAllStoresUseCase getAllStoresUseCase(StoreRepository repository) {
        return new GetAllStoresUseCase(repository);
    }

    @Bean
    public GetStoresByCousineUseCase getStoresByCousineUseCase(CousineRepository repository) {
        return new GetStoresByCousineUseCase(repository);
    }

    @Bean
    public GetAllCousinesUseCase getAllCousinesUseCase(CousineRepository repository) {
        return new GetAllCousinesUseCase(repository);
    }

    @Bean
    public SearchCousineByNameUseCase searchCousineByNameUseCase(CousineRepository repository) {
        return new SearchCousineByNameUseCase(repository);
    }
}
