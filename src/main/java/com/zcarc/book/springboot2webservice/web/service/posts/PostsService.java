package com.zcarc.book.springboot2webservice.web.service.posts;

import com.zcarc.book.springboot2webservice.web.domain.posts.Posts;
import com.zcarc.book.springboot2webservice.web.domain.posts.PostsRepository;
import com.zcarc.book.springboot2webservice.web.dto.PostsResponseDto;
import com.zcarc.book.springboot2webservice.web.dto.PostsSaveRequestDto;
import com.zcarc.book.springboot2webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        Posts savedEntity = postsRepository.save(requestDto.toEntity());
        return savedEntity.getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
