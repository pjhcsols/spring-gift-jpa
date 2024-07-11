package gift.product.application;

import gift.exception.type.KakaoInNameException;
import gift.exception.type.NotFoundException;
import gift.product.application.command.ProductCreateCommand;
import gift.product.application.command.ProductUpdateCommand;
import gift.product.domain.Product;
import gift.product.domain.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
        reset(productRepository);
    }

    @Test
    public void 모든_상품_조회_테스트() {
        // Given
        Product product = new Product("Product1", 1000, "http://example.com/image1.jpg");
        when(productRepository.findAll()).thenReturn(List.of(product));

        // When
        List<ProductResponse> products = productService.findAll();

        // Then
        assertThat(products).hasSize(1);
        assertThat(products.get(0).name()).isEqualTo("Product1");
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void 상품_ID로_조회_테스트() {
        // Given
        Product product = new Product("Product1", 1000, "http://example.com/image1.jpg");
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));

        // When
        ProductResponse productResponse = productService.findById(1L);

        // Then
        assertThat(productResponse.name()).isEqualTo("Product1");
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void 상품_ID로_조회_실패_테스트() {
        // Given
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // When / Then
        assertThatThrownBy(() -> productService.findById(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("해당 상품이 존재하지 않습니다.");
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void 상품_추가_테스트() {
        // Given
        ProductCreateCommand createCommand = new ProductCreateCommand("Product1", 1000, "http://example.com/image1.jpg");
        Product product = createCommand.toProduct();
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // When
        productService.save(createCommand);

        // Then
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void 상품_업데이트_테스트() {
        // Given
        Product product = new Product("Product1", 1000, "http://example.com/image1.jpg");
        ProductUpdateCommand updateCommand = new ProductUpdateCommand(1L, "UpdatedProduct", 2000, "http://example.com/image2.jpg");
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));

        // When
        productService.update(updateCommand);

        // Then
        verify(productRepository, times(1)).findById(1L);
        assertThat(product.getName()).isEqualTo("UpdatedProduct");
        assertThat(product.getPrice()).isEqualTo(2000);
        assertThat(product.getImageUrl()).isEqualTo("http://example.com/image2.jpg");
    }

    @Test
    public void 상품_업데이트_실패_테스트() {
        // Given
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        ProductUpdateCommand updateCommand = new ProductUpdateCommand(1L, "UpdatedProduct", 2000, "http://example.com/image2.jpg");

        // When / Then
        assertThatThrownBy(() -> productService.update(updateCommand))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("해당 상품이 존재하지 않습니다.");
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void 상품_삭제_테스트() {
        // Given
        Product product = new Product("Product1", 1000, "http://example.com/image1.jpg");
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(any(Product.class));

        // When
        productService.delete(1L);

        // Then
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void 이름에카카오포함시_상품생성_실패_테스트() {
        // Given
        ProductCreateCommand createCommand = new ProductCreateCommand("카카오가 포함된 이름", 1000, "http://example.com/image1.jpg");

        // When / Then
        assertThatThrownBy(() -> productService.save(createCommand))
                .isInstanceOf(KakaoInNameException.class)
                .hasMessage("카카오가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다.");
    }

    @Test
    public void 이름에카카오포함시_상품업데이트_실패_테스트() {
        // Given
        Product product = new Product("Product1", 1000, "http://example.com/image1.jpg");
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));
        ProductUpdateCommand updateCommand = new ProductUpdateCommand(1L, "카카오가 포함된 이름", 2000, "http://example.com/image2.jpg");

        // When / Then
        assertThatThrownBy(() -> productService.update(updateCommand))
                .isInstanceOf(KakaoInNameException.class)
                .hasMessage("카카오가 포함된 문구는 담당 MD와 협의한 경우에만 사용할 수 있습니다.");
    }
}
