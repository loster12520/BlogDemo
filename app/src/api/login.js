import request from '@/api/request'

export const login = (data) => {
    return request.post('/public/login', data);
};

export const register = (data) => {
    return request.post('/public/register', data);
}