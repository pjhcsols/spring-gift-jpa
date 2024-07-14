package gift.repository;

import gift.model.WishList;
import gift.model.Member;
import gift.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByMember(Member member);
    Optional<WishList> findByMemberAndProduct(Member member, Product product);
}