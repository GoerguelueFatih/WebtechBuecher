import { describe, it, expect, beforeEach, afterEach, vi } from 'vitest';
import { mount } from '@vue/test-utils';
import SearchBooks from '../BookSearch.vue';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

vi.mock('@okta/okta-vue', () => ({
    useAuth: () => ({
        isAuthenticated: vi.fn().mockResolvedValue(true),
        getUser: vi.fn().mockResolvedValue({ sub: 'userId' }),
    }),
}));

let mock;

beforeEach(() => {
    mock = new MockAdapter(axios);
});

afterEach(() => {
    mock.restore();
});

describe('searchBooks Component', () => {
    it('should display books after search', async () => {
        const searchTerm = 'Harry Potter';
        const mockData = [{id: 1, title: 'Harry Potter and the Sorcerers Stone', author: 'J.K. Rowling', price: 10}];

        mock.onGet(`http://localhost:8080/books/search?title=${encodeURIComponent(searchTerm)}`).reply(200, mockData);

        const wrapper = mount(SearchBooks, {
            global: {
                mocks: {
                    useAuth: () => ({
                        isAuthenticated: vi.fn().mockResolvedValue(true),
                        getUser: vi.fn().mockResolvedValue({ sub: 'userId' }),
                    }),
                },
            },
        });

        wrapper.vm.searchTerm = searchTerm;
        await wrapper.vm.searchBooks();

        await new Promise(setImmediate);
        await wrapper.vm.$nextTick();

        expect(wrapper.vm.books).toEqual(mockData);
        expect(wrapper.html()).toContain('Harry Potter and the Sorcerers Stone');
    });
});
