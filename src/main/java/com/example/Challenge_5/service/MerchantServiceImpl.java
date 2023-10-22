package com.example.Challenge_5.service;

import com.example.Challenge_5.model.Merchant;
import com.example.Challenge_5.repository.MerchantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService{

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Merchant addMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant updateMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public void deleteMerchant(String MerchantCode) {
        merchantRepository.deleteById(MerchantCode);
    }

    @Override
    public List<Merchant> getAllMerchant() {
        return merchantRepository.findAll();
    }

    @Override
    public Merchant getMerchantDetail(String selectedMerchantCode) {
        log.info("Getting merchant detail info of {}", selectedMerchantCode);
        return Optional.ofNullable(merchantRepository.findById(selectedMerchantCode))
                .map(merchant -> Merchant.builder()
                        .MerchantCode(merchant.get().getMerchantCode())
                        .MerchantName(merchant.get().getMerchantName())
                        .MerchantLocation(merchant.get().getMerchantLocation())
                        .open(true)
                        .build())
                .orElse(null);
    }

}
