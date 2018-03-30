package com.faceye.feature.repository.jpa.impl;

import static org.springframework.data.querydsl.QuerydslUtils.*;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
@NoRepositoryBean
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable> extends
		JpaRepositoryFactoryBean<R, T, ID> {
	public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
	}

	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new BaseRepositoryFactory(entityManager);
	}

	private static class BaseRepositoryFactory extends JpaRepositoryFactory {
		private final EntityManager entityManager;

		public BaseRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;

		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata,
				EntityManager entityManager) {
			Class<?> repositoryInterface = metadata.getRepositoryInterface();
			JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());

			SimpleJpaRepository<?, ?> repo = null;
			Boolean isQueryDslExecutor = isQueryDslExecutor(repositoryInterface);
			if (isQueryDslExecutor) {
				repo = new QuerydslJpaRepository(entityInformation, entityManager);
			} else {
				repo = new BaseRepositoryImpl(entityInformation, entityManager);
			}
			return repo;
		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			if (isQueryDslExecutor(metadata.getRepositoryInterface())) {
				return QuerydslJpaRepository.class;
			} else {
//				return BaseRepository.class;
				return BaseRepositoryImpl.class;
			}
		}

		private boolean isQueryDslExecutor(Class<?> repositoryInterface) {
			return QUERY_DSL_PRESENT && QuerydslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
		}
	}
}
