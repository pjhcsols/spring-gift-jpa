package gift.controller;

import gift.auth.CheckRole;
import gift.auth.LoginMember;
import gift.request.LoginMemberDto;
import gift.response.ProductResponse;
import gift.request.WishListRequest;
import gift.exception.InputException;
import gift.model.Product;
import gift.model.WishProduct;
import gift.repository.WishProductDao;
import gift.service.WishProductService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishListApiController {

    private final WishProductService wishProductService;

    public WishListApiController(WishProductService wishProductService) {
        this.wishProductService = wishProductService;
    }

    @CheckRole("ROLE_USER")
    @GetMapping("/api/wishlist")
    public ResponseEntity<List<ProductResponse>> getWishList(
        @LoginMember LoginMemberDto memberDto) {
        List<ProductResponse> dtoList;
        List<Product> wishlist = wishProductService.getMyWishList(memberDto.id());

        dtoList = wishlist.stream()
            .map(ProductResponse::new)
            .toList();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @CheckRole("ROLE_USER")
    @PostMapping("/api/wishlist")
    public ResponseEntity<Void> addWishList(@LoginMember LoginMemberDto memberDto,
        @RequestBody @Valid WishListRequest dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputException(bindingResult.getAllErrors());
        }

        wishProductService.addMyWish(memberDto.id(), dto.productId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CheckRole("ROLE_USER")
    @DeleteMapping("/api/wishlist")
    public ResponseEntity<Void> deleteWishList(@LoginMember LoginMemberDto memberDto,
        @RequestBody @Valid WishListRequest dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new InputException(bindingResult.getAllErrors());
        }

        wishProductService.removeMyWish(memberDto.id(), dto.productId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}