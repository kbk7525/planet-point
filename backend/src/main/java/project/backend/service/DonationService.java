package project.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.Donation;
import project.backend.domain.User;
import project.backend.dto.DonationDTO;
import project.backend.repository.DonationRepository;
import project.backend.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    public DonationDTO findByElementId(Long elementId) {
        Donation donation = donationRepository.findById(elementId).orElse(null);
        if(donation != null) {
            return new DonationDTO(donation.getId(), donation.getElementId(),
                    donation.getDonationName(), donation.getSeedCnt());
        }
        return null;
    }

    @Transactional
    public void increaseElementSeed(Long elementId, int cnt) {
        Optional<Donation> element = donationRepository.findByElementId(elementId);
        if(element.isEmpty()) {
            throw new RuntimeException("해당 기부 항목이 없습니다.");
        }
        Donation existElement = element.get();
        int currentSeed = existElement.getSeedCnt();
        existElement.setSeedCnt(currentSeed+cnt);
        donationRepository.save(existElement);
    }

    @Transactional
    public void decreaseUserSeed(String email, int cnt) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        User existUser = user.get();
        int currentSeed = existUser.getSeed();
        if(currentSeed < cnt) {
            throw new RuntimeException("씨앗이 부족합니다.");
        }
        existUser.setSeed(currentSeed-cnt);
        userRepository.save(existUser);
    }
}